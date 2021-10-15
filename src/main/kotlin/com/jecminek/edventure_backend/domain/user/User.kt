package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.base.BaseEntity
import com.jecminek.edventure_backend.domain.contact.Contact
import com.jecminek.edventure_backend.domain.offer.Offer
import com.jecminek.edventure_backend.domain.review.Review
import com.jecminek.edventure_backend.domain.score.Score
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "\"USER\"")
class User(
    var firstName: String = "",
    var lastName: String = "",

    @Column(unique = true) var userName: String = "",

    private var password: String = "",
    private var enabled: Boolean = true,
    private var locked: Boolean = false,
    private var expired: Boolean = false,

    // TODO: 12.10.2021 HOW TO ADD AUTHORITIES TO USER
    private var authorities: Role? = null,


    /** (DO NOT TOUCH) These lists are only for compatibility issue. **/

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewer", cascade = [CascadeType.ALL])
    var reviewerReviews: MutableList<Review> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewed", cascade = [CascadeType.ALL])
    var reviewedReviews: MutableList<Review> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = [CascadeType.ALL])
    var userContacts: MutableList<Contact> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = [CascadeType.ALL])
    var userOffers: MutableList<Offer> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = [CascadeType.ALL])
    var userScores: MutableList<Score> = mutableListOf()

    /** (DO NOT TOUCH) These lists are only for compatibility issue. **/

) : BaseEntity(), UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        // TODO: ZPRACOVAT
        val list: MutableCollection<GrantedAuthority> = HashSet()
        if (authorities != null) {
            list.add(authorities!!)
        }
        return list
    }

    override fun getPassword(): String = this.password

    fun setPassword(password: String) {
        this.password = password
    }

    override fun getUsername(): String = this.userName

    override fun isAccountNonExpired(): Boolean = !this.expired

    override fun isAccountNonLocked(): Boolean = !this.locked

    override fun isCredentialsNonExpired(): Boolean = !this.expired

    override fun isEnabled(): Boolean = this.enabled
}

fun User.convertEntityToResponse() = UserResponse(
    id = id,
    firstName = firstName,
    lastName = lastName,
    userName = userName,
)

fun User.convertEntityToReviewResponse() = UserReviewResponse(
    id = id,
    firstName = firstName,
    lastName = lastName
)






