package ar.com.leandroamarillo.productfinder.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ar.com.leandroamarillo.productfinder.R
import ar.com.leandroamarillo.productfinder.databinding.FragmentProductDetailBinding
import com.bumptech.glide.Glide

class ProductDetailFragment : Fragment() {
    val args: ProductDetailFragmentArgs by navArgs()
    lateinit var binding: FragmentProductDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false)
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.product_detail)

        val viewModelFactory = ProductDetailViewModelFactory(args.product)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(ProductDetailViewModel::class.java)

        binding.productDetailViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.product.observe(viewLifecycleOwner, Observer {
            it.let {
                binding.marcadoPagoText.text =
                    if (it.acceptsMercadopago) getString(R.string.mp_yes) else getString(R.string.mp_no)
                Glide.with(this).load(it.thumbnail).into(binding.productImage)
            }
        })

        return binding.root
    }
}