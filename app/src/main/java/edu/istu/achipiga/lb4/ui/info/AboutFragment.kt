package edu.istu.achipiga.lb4.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.istu.achipiga.lb4.BuildConfig
import edu.istu.achipiga.lb4.R
import edu.istu.achipiga.lb4.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textVersion.text = getString(R.string.about_version_label) + ": " + BuildConfig.VERSION_NAME
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
