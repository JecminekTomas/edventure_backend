package com.jecminek.edventure_backend.domain.lesson

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Size

data class LessonDto(
    @Schema(description = "ID is only for response ")
    var id: Long,

    /**An annotation argument must be a compile-time constant, if not System.currentTimeMillis() / 1000L*/
    // TODO: 28.03.2021 Event should not take more than SOMETIME.

    @Min(1616960347)
    @Schema(description = "Start time of lesson in UNIX TIMESTAMP stored as Long", example = "1616960347")
    var startTimestamp: Long,

    @Min(1616960347)
    @Schema(description = "End time of lesson in UNIX TIMESTAMP stored as Long", example = "1616961347")
    var endTimestamp: Long,

    @Size(min = 4, max = 100, message = "Describe the place in 4 to 100 characters")
    @Schema(description = "Description of place where lesson will be", example = "14 St James's Square, St. James's, London SW1Y 4LG")
    var place: String?,

    @Min(0, message = "Price cannot be negative") @Max(100000, message = "Not worth it")
    @Schema(description = "Price of lecture", example = "200")
    var price: Double,

    @Size(min = 4, max = 100, message = "Note can be from 4 to 280 characters long")
    @Schema(description = "Additional notes", example = "Bring the computer!")
    var note: String?,

    @Schema(description = "List of teachers ids at lecture", example = "[\"1\"]")
    var teachersIds: MutableList<Long>,

    @Schema(description = "List of students ids at lecture", example = "[\"2\"]")
    var studentsIds: MutableList<Long>,

    @Schema(description = "ID of taught subject")
    var subjectId: Long

)