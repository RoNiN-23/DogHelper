package com.example.doghelper.ui.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate.NightMode
import com.example.doghelper.R
import com.example.doghelper.constance.Constance
import com.example.doghelper.databinding.FragmentMapBinding
import com.example.doghelper.databinding.FragmentProfileBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

class MapFragment : Fragment() {

    private lateinit var mapView: MapView
    lateinit var binding: FragmentMapBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        MapKitFactory.initialize(activity)
        binding = FragmentMapBinding.inflate(inflater, container, false)
        var root = binding.root
        mapView = binding.mapView

        mapView.map.move(
            CameraPosition(Point(55.604519, 37.584092), 11.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 1f),
            null
        )
        return root
    }

    override fun onStart() {
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MapFragment()
    }
}