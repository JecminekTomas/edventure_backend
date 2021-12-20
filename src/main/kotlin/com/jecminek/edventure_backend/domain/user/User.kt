package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.base.BaseEntity
import com.jecminek.edventure_backend.domain.contact.Contact
import com.jecminek.edventure_backend.domain.offer.Offer
import com.jecminek.edventure_backend.domain.review.Review
import com.jecminek.edventure_backend.domain.score.Score
import com.jecminek.edventure_backend.enums.AuthorityType

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*


@Entity
@Table(name = "\"USER\"")
class User(
    @Column(nullable = false)
    var firstName: String,

    @Column(nullable = false)
    var lastName: String,

    @Column(
        unique = true,
        nullable = false
    )
    var userName: String,

    @Column(nullable = false)
    private var password: String,
    private var enabled: Boolean,
    private var locked: Boolean,
    private var expired: Boolean,

    private var authority: String? = null,


    /** (DO NOT TOUCH) These lists are only for compatibility issue. **/

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userFrom", cascade = [CascadeType.ALL])
    var userFromReviews: MutableList<Review> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userTo", cascade = [CascadeType.ALL])
    var userToReviews: MutableList<Review> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = [CascadeType.ALL])
    var userContacts: MutableList<Contact> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = [CascadeType.ALL])
    var userOffers: MutableList<Offer> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = [CascadeType.ALL])
    var userScores: MutableList<Score> = mutableListOf()

    /** (DO NOT TOUCH) These lists are only for compatibility issue. **/

) : BaseEntity(), UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = hashSetOf(GrantedAuthority { authority })

    override fun getPassword(): String = this.password

    fun setPassword(password: String) {
        this.password = password
    }

    fun setAuthority(authorityType: AuthorityType) {
        this.authority = authorityType.toString()
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






