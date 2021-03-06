package ar.com.leandroamarillo.productfinder.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.leandroamarillo.productfinder.R
import ar.com.leandroamarillo.productfinder.databinding.FragmentProductListBinding
import timber.log.Timber


class ProductListFragment : Fragment() {
    val args: ProductListFragmentArgs by navArgs()
    lateinit var binding: FragmentProductListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.products)

        val viewModelFactory = ProductListViewModelFactory(args.query)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(ProductListViewModel::class.java)

        binding.productListViewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = ProductAdapter(ProductAdapter.OnClickListener {
            //Navigate to details
            viewModel.navigateToProduct(it)
        })
        binding.productList.adapter = adapter
        binding.productList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                recyclerView.layoutManager?.let {
                    val totalItemCount = it.itemCount
                    val lastItemViewed =
                        (it as LinearLayoutManager).findLastVisibleItemPosition() + 1

                    // Search next page if we are at the end and not searching already
                    if (totalItemCount == lastItemViewed && viewModel.busy.value == false) {
                        viewModel.searchNextPage()
                    }
                }
            }
        })

        viewModel.busy.observe(viewLifecycleOwner, Observer {
            it.let {
                binding.querySearching.visibility = if (it) View.VISIBLE else View.GONE
                binding.queryText.text = if (it)
                    getString(R.string.searching_query, args.query)
                else
                    getString(R.string.results_query, args.query)

            }
        })

        viewModel.errorVisible.observe(viewLifecycleOwner, Observer {
            it.let {
                binding.errorText.visibility = if (it) View.VISIBLE else View.GONE
                binding.errorText.text = viewModel.error.value
            }
        })

        viewModel.navigateToProduct.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController().navigate(
                    ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
                        it
                    )
                )
                viewModel.navigateToProductHandled()
            }
        })

        viewModel.newProducts.observe(viewLifecycleOwner, Observer {
            it.let {
                if(it){
                    adapter.notifyDataSetChanged()
                    Timber.i("New data, telling the adapter to update")
                }
            }
        })

        viewModel.displayNoMoreProducts.observe(viewLifecycleOwner, Observer {
            it.let {
                if(it){
                    viewModel.noMoreProductsHandled()
                    Toast.makeText(this.context, getText(R.string.no_more_products), Toast.LENGTH_SHORT).show()
                }
            }
        })
        return binding.root
    }
}