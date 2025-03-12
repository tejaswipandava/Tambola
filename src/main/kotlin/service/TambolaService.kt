package service

import exception.InvalidDataException
import model.Ticket
import validation.FirstFiveValidator
import validation.FullHouseValidator
import validation.RowValidator

class TambolaService {

    fun claim(ticket: Ticket, announcedNumber: Set<Int>, claim: String): String {
        val claimValidator = when (claim.lowercase()) {
            "top row", "middle row", "bottom row" -> RowValidator()
            "full house" -> FullHouseValidator()
            "early five" -> FirstFiveValidator()
            else -> throw InvalidDataException("invalid claim")
        }
        return if (claimValidator.validate(ticket, announcedNumber, claim)) "accepted" else "rejected"
    }
}