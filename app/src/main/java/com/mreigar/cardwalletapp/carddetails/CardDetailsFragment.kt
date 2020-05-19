package com.mreigar.cardwalletapp.carddetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mreigar.cardwalletapp.R
import kotlinx.android.synthetic.main.fragment_card_details.*

class CardDetailsFragment : Fragment() {

    private val args: CardDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(args.cardViewModel) {
            cardDetailsName.text = name
            cardDetailsNumber.text = number
            cardDetailsExpiryDate.text = expiryDate
            cardDetailsCvv.text = cvv
            cardDetailsImage.setImageResource(iconResId)
        }
    }
}