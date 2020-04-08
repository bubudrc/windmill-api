//
//  Created by Markos Charatzas (markos@qnoid.com)
//  Copyright © 2014-2020 qnoid.com. All rights reserved.
//
//  The above copyright notice and this permission notice shall be included in
//  all copies or substantial portions of the Software.
//
//  Permission is granted to anyone to use this software for any purpose,
//  including commercial applications, and to alter it and redistribute it
//  freely, subject to the following restrictions:
//
//  This software is provided 'as-is', without any express or implied
//  warranty.  In no event will the authors be held liable for any damages
//  arising from the use of this software.
//
//  1. The origin of this software must not be misrepresented; you must not
//     claim that you wrote the original software. If you use this software
//     in a product, an acknowledgment in the product documentation is required.
//  2. Altered source versions must be plainly marked as such, and must not be
//     misrepresented as being the original software.
//  3. This notice may not be removed or altered from any source distribution.

package io.windmill.windmill.persistence.web;

import java.time.Instant;

import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.windmill.windmill.persistence.Subscription;
import io.windmill.windmill.web.JsonbAdapterInstantToEpochSecond;

@Entity
@Table(name="subscription_authorization_token")
public class SubscriptionAuthorizationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="created_at")
    private Instant createdAt;

    @Column(name="accessed_at")
    private Instant accessedAt;

    @OneToOne(cascade = CascadeType.PERSIST)    
    @NotNull
    @JoinColumn(name="authorization_token_id")
    AuthorizationToken authorizationToken;

    @OneToOne
    @JoinColumn(name="subscription_id")
    @NotNull
    Subscription subscription;
    
	public SubscriptionAuthorizationToken() {
		this.authorizationToken = AuthorizationToken.create();
        this.createdAt = Instant.now();
	}
	
	public SubscriptionAuthorizationToken(Subscription subscription) {
		super();
		this.authorizationToken = AuthorizationToken.create();
		this.subscription = subscription;
        this.createdAt = Instant.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	@JsonbTypeAdapter(JsonbAdapterInstantToEpochSecond.class)
	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	@JsonbTypeAdapter(JsonbAdapterInstantToEpochSecond.class)
	public Instant getAccessedAt() {
		return accessedAt;
	}

	public void setAccessedAt(Instant accessedAt) {
		this.accessedAt = accessedAt;
	}

	public AuthorizationToken getAuthorizationToken() {
		return authorizationToken;
	}

	public void setAuthorizationToken(AuthorizationToken authorizationToken) {
		this.authorizationToken = authorizationToken;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public Instant getExpiresAt() {
		return this.subscription.getExpiresAt();
	}	

	@Override
	public String toString() {
		return this.authorizationToken.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorizationToken == null) ? 0 : authorizationToken.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that)
			return true;
		
		if (!(that instanceof SubscriptionAuthorizationToken))
			return false;
		
		SubscriptionAuthorizationToken subscriptionAuthorizationToken = (SubscriptionAuthorizationToken) that;
		
		return this.authorizationToken.equals(subscriptionAuthorizationToken.authorizationToken); 
	}
}
