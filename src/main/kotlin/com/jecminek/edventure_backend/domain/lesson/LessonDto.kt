package com.jecminek.edventure_backend.domain.lesson

import com.jecminek.edventure_backend.domain.user.UserIdDto
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class LessonDto(
    @Schema(description = "ID is only for response")
    var id: Long,

    /**An annotation argument must be a compile-time constant, if not System.currentTimeMillis() / 1000L*/
    // TODO: 28.03.2021 Event should not take more than SOMETIME.

    @Min(1616960347)
    @Schema(description = "Start time of lesson in UNIX TIMESTAMP stored as Long", example = "1616960347")
    var startTimestamp: Long,

    @Min(1616960347)
    @Schema(description = "End time of lesson in UNIX TIMESTAMP stored as Long", example = "1616961347")
    var endTimestamp: Long,

    @Min(0, message = "Price cannot be negative") @Max(100000, message = "Not worth it")
    @Schema(description = "Price of lecture", example = "200")
    var price: Double,

    @Schema(description = "Says, if lecture will be online, or not", example = "true")
    var online: Boolean,

    var teachers: MutableList<UserIdDto>,
    var students: MutableList<UserIdDto>

)