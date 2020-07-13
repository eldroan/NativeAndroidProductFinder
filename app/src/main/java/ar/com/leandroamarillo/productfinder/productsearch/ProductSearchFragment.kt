package ar.com.leandroamarillo.productfinder.productsearch

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import ar.com.leandroamarillo.productfinder.R
import ar.com.leandroamarillo.productfinder.databinding.FragmentProductSearchBinding

class ProductSearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentProductSearchBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_product_search,
            container,
            false
        )

        //Set screen title
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.search_products)

        //Build view-model
        val viewModelFactory = ProductSearchViewModelFactory()
        val viewModel =
            ViewModelProvider(this, viewModelFactory)
                .get(ProductSearchViewModel::class.java)
        binding.productSearchViewModel = viewModel
        binding.lifecycleOwner = this

        // Setup navigation to next screen
        viewModel.shouldNavigateToList.observe(viewLifecycleOwner, Observer {
            if (it) {
                viewModel.navigationHandled()
                closeKeyboard()
                //Navigate to product screen
                view?.findNavController()?.navigate(
                    ProductSearchFragmentDirections.actionProductSearchFragmentToProductListFragment(
                        viewModel.search.value!!
                    )
                )
            }
        })

        return binding.root
    }

    private fun closeKeyboard() {
        val imm =
            (activity as AppCompatActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}