package model

import model.enums.Country
import model.enums.Gender
import java.time.LocalDate
import java.util.*

// an explorer : object which is immutable after construction
data class Explorer (val id: UUID,
                     val name: String,
                     val birthDate: LocalDate,
                     val country: Country,
                     val gender: Gender
)