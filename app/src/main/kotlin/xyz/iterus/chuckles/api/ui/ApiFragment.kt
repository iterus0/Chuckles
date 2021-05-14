package xyz.iterus.chuckles.api.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import xyz.iterus.chuckles.R
import xyz.iterus.chuckles.api.data.network.ICNDbApi
import xyz.iterus.chuckles.databinding.FragmentApiBinding

class ApiFragment : Fragment(R.layout.fragment_jokes) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentApiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentApiBinding.bind(view)

        if (savedInstanceState != null) {
            val webViewState = savedInstanceState.getBundle("webViewState")
            if (webViewState != null) {
                binding.apiWebView.restoreState(webViewState)
            }
        } else {
            binding.apiWebView.loadUrl(ICNDbApi.BASE_URL)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val view = this.view
        if (view != null) {
            val binding = FragmentApiBinding.bind(view)

            val webViewState = Bundle()
            binding.apiWebView.saveState(webViewState)
            outState.putBundle("webViewState", webViewState)
        }
    }
}
