package com.example.lista2

data class Crime(val solveState: Boolean, val title: String, val description: String, val index: Int ) {

}


fun generateListOfCrimes(): MutableList<Crime> {
    var listOfCrimes = mutableListOf<Crime>()
    for (i: Int in 0..20) {
        if(i%2 == 0)
            listOfCrimes.add(Crime(true, "Crime #$i", "This is crime number $i done by student of index: $i", i))
        else
            listOfCrimes.add(Crime(false, "Crime #$i", "This is crime number $i done by student of index: $i", i))
    }
    return listOfCrimes
}