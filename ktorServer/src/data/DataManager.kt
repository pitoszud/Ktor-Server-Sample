package com.velocip.io.data

import com.velocip.io.domain.SwimmingEvent
import java.util.*

class DataManager {

    var events = mutableListOf(
        SwimmingEvent(id = "6d7afb7d-477b-4db2-b526-b586bc58cf83", name = "MON swim", capacity = 101, price = 5.50),
        SwimmingEvent(id = "14b9185f-fa38-4f01-a238-141ad4d3ce73", name = "TUE swim", capacity = 102, price = 5.50),
        SwimmingEvent(id = "262cd7a2-8aab-49bc-962d-9b0d8c0ce995", name = "WED swim", capacity = 103, price = 5.50),
        SwimmingEvent(id = "7e5cc5be-abbb-4f19-8ded-a0e128a1ed0b", name = "THU swim", capacity = 104, price = 5.50),
        SwimmingEvent(id = "ea69725c-7642-47a3-bf8c-97b8ac825f73", name = "FRI swim", capacity = 105, price = 5.50),
        SwimmingEvent(id = "508f6a91-96de-4103-82b5-43891ff4118b", name = "SAT swim", capacity = 106, price = 5.50),
        SwimmingEvent(id = "3b3ccead-48af-4e7f-a85f-0382dbecb562", name = "SUN swim", capacity = 107, price = 5.50)
    )

    fun addEvent(swimmingEvent: SwimmingEvent){
        events.add(swimmingEvent)
    }

    fun removeEvent(id: String){
        events.removeIf { it.id == id }
    }

    fun updateEvent(event: SwimmingEvent){
        events.map {
            if (it.id == event.id){
                it.name = event.name
            }
        }
    }

    fun allEvents() = events

}