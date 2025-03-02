package Helper

import model.Ticket

class ClaimHelper {

    fun generateTicket(): Ticket {
        return Ticket(listOf(setOf(1, 2, 3, 4, 5), setOf(6, 7, 8, 9, 10), setOf(11, 12, 13, 14, 15)))
    }
}