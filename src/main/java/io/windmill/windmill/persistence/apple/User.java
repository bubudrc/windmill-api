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

package io.windmill.windmill.persistence.apple;

import java.time.Instant;

import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.windmill.windmill.persistence.Account;
import io.windmill.windmill.web.JsonbAdapterInstantToEpochSecond;

@Entity
@Table(schema="apple",name="user")
@NamedQuery(name = "user.find_by_identifier", query = "SELECT u FROM User u WHERE u.identifier = :identifier")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    @NotNull
    private String identifier;

    @NotNull
    private String container;

    @Column(name="created_at")
    @NotNull
    private Instant createdAt;

    @ManyToOne(cascade = CascadeType.PERSIST)
    Account account;
    
    /**
     * 
     */
    public User()
    {
    }

	public User(String identifier, String container) {
		this.identifier = identifier;
		this.container = container;
		this.account = new Account();
		this.createdAt = Instant.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
       
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}	

	public String getContainer() {
		return container;
	}

	public void setContainer(String container) {
		this.container = container;
	}

	@JsonbTypeAdapter(JsonbAdapterInstantToEpochSecond.class)
	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that)
			return true;
		
		if (!(that instanceof User))
			return false;
		
		User user = (User) that;
		
		return this.identifier.equals(user.identifier);
	}
}
