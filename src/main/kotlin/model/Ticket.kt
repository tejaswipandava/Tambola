package model

class Ticket(val row: List<Set<Int>>) {
    init {
        require(row.flatten().toSet().size == 15) { "Elements must be unique across all sets" }
        require(row.all { it.size == 5 }) { "Each set must contain exactly 5 elements" }
    }
}
