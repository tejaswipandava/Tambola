package validation

import exception.InvalidDataException
import model.Ticket
import java.util.*

class RowValidator : ClaimValidator {
    override fun validate(ticket: Ticket, announcedNumber: List<Int>, claim: String): Boolean {
        var count = 0
        val row: Set<Int> = when (claim.lowercase()) {
            "top row" -> ticket.row[0]
            "middle row" -> ticket.row[1]
            "bottom row" -> ticket.row[2]
            else -> throw InvalidDataException("invalid claim")
        }

        announcedNumber.forEachIndexed { index, value ->
            if (row.contains(value)) count++
            if (count == 5) {
                return index == announcedNumber.lastIndex
            }
        }

        return false
    }
}