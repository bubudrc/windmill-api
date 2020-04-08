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

package io.windmill.windmill.web.security;

import java.util.Optional;

public enum MacAlgorithm {
	HMAC_SHA256("HmacSHA256", "HS256");
	
	final String system;
	final String canonical;

	MacAlgorithm(String system, String canonical) {
		this.system = system;
		this.canonical = canonical;
	}
	
	public static Optional<MacAlgorithm> of(String algorithm) {
        for (MacAlgorithm a : MacAlgorithm.values()) {
            if (a.system.equals(algorithm) || a.canonical.equals(algorithm)) {
                return Optional.of(a);
            }
        }

		return Optional.empty();
	}
    
    @Override
    public String toString() {
        return this.canonical;
    }
}