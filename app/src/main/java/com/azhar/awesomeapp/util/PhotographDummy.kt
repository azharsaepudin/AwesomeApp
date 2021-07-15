package com.azhar.awesomeapp.util

import com.azhar.awesomeapp.core.domain.model.DataPhotos

object PhotographDummy {

    fun dummyFotograph(): ArrayList<DataPhotos>{

        val dataPhotograph = ArrayList<DataPhotos>()

        dataPhotograph.add(
            DataPhotos(
                1966686,
                3264,
                4928,
                "https://www.pexels.com/photo/fashion-man-people-woman-1966686/",
                "Jessica Nunes",
                "https://www.pexels.com/@jessicanunes",
                914707,
                "#8C94A3",
                "https://images.pexels.com/photos/1966686/pexels-photo-1966686.jpeg?auto=compress&cs=tinysrgb&h=130",
                "https://images.pexels.com/photos/1966686/pexels-photo-1966686.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                false


            )
        )

        return dataPhotograph
    }

}