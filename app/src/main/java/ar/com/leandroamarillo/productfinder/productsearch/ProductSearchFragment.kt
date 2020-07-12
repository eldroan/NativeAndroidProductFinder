package ar.com.leandroamarillo.productfinder.productsearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ar.com.leandroamarillo.productfinder.R
import ar.com.leandroamarillo.productfinder.databinding.FragmentProductSearchBinding

class ProductSearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentProductSearchBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_search, container, false)
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.buscar_productos)

        val viewModelFactory = ProductSearchViewModelFactory()
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(ProductSearchViewModel::class.java)
        binding.productSearchViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.busy.observe(viewLifecycleOwner, Observer { busy ->
            binding.progressBar.visibility = if (busy) View.VISIBLE else View.GONE
            binding.resultText.visibility = if (busy) View.GONE else View.VISIBLE
        })

        return binding.root
    }
}