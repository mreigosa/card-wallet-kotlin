package com.mreigar.cardwalletapp.addcard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mreigar.cardwalletapp.R
import com.mreigar.cardwalletapp.customview.CardDateTextWatcher
import com.mreigar.cardwalletapp.customview.CardNumberTextWatcher
import com.mreigar.cardwalletapp.gone
import com.mreigar.cardwalletapp.hideKeyboard
import com.mreigar.cardwalletapp.visible
import com.mreigar.data.Error
import com.mreigar.data.Result
import com.mreigar.data.Success
import kotlinx.android.synthetic.main.fragment_add_card.*
import org.koin.android.viewmodel.ext.android.viewModel

class AddCardFragment : Fragment() {

    private val viewModel: AddCardViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
//            findNavController().navigateUp()
//        }


//            .addCallback(viewLifecycleOwner) {
//            findNavController().popBackStack()
//        }

        cardNumberInputLayout.editText?.addTextChangedListener(CardNumberTextWatcher(cardImage))
        expiryDateInputLayout.editText?.addTextChangedListener(CardDateTextWatcher())

        addCardButton.setOnClickListener {
            viewModel.addCardToWallet(
                cardNameInputLayout.editText?.text.toString(),
                cardNumberInputLayout.editText?.text.toString(),
                expiryDateInputLayout.editText?.text.toString(),
                cvvInputLayout.editText?.text.toString()
            )
        }

        viewModel.state.observe(viewLifecycleOwner, Observer {
            handleLoader(it.isLoading)

            handleResult(it.addCardResult)
        })
    }

    private fun handleResult(addCardResult: Result<Boolean>?) {
        addCardResult?.let {
            when (it) {
                is Success -> {
                    addCardButton.hideKeyboard()
                    findNavController().popBackStack()
                }
                is Error -> Snackbar.make(addCardLayout, "Add credit card error: ${it.exception.message}", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun handleLoader(isLoading: Boolean) = if (isLoading) addCardLoader.visible() else addCardLoader.gone()
}