package model

import model.enums.Country
import model.enums.Gender
import java.time.LocalDate
import java.util.*

// an explorer : object which is immutable after construction
data class Explorer (var id: UUID? = null,
                     var name: String? = null,
                     var birthDate: LocalDate? = null,
                     var country: Country? = null,
                     var gender: Gender? = null
)