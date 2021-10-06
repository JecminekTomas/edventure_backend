package com.jecminek.edventure_backend.domain.score

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class ScoreController {

    @Autowired
    lateinit var service: ScoreService

    @Operation(summary = "Finds all scores with requested parameters")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ScoreDTO::class)))]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(
                responseCode = "404",
            )]
    )
    @GetMapping("/scores")
    fun findScoresByReviewId(
        @Parameter(
            description = "Id of review, which score belongs to.",
            example = "1"
        )
        @RequestParam(required = true) reviewId: Long
    ): List<ScoreDTO> = service.findScoresByReviewId(reviewId)

    @Operation(summary = "Create score")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ScoreDTO::class)))
                ]
            ),
            ApiResponse(responseCode = "400")
        ]
    )

    @PostMapping("/scores")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(scoreDTO: ScoreDTO): ScoreDTO = service.create(scoreDTO)

    @Operation(summary = "Update score")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "202",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ScoreDTO::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @PutMapping("/scores/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(id: Long, scoreDTO: ScoreDTO) = service.update(id, scoreDTO)

    @Operation(summary = "Delete score")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204"),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @DeleteMapping("/scores/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.delete(id)
}