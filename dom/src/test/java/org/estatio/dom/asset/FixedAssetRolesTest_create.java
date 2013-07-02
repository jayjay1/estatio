/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.estatio.dom.asset;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.core.unittestsupport.jmocking.JUnitRuleMockery2;
import org.apache.isis.core.unittestsupport.jmocking.JUnitRuleMockery2.Mode;

import org.estatio.dom.party.Party;
import org.estatio.dom.party.PartyForTesting;

public class FixedAssetRolesTest_create {

    @Rule
    public JUnitRuleMockery2 context = JUnitRuleMockery2.createFor(Mode.INTERFACES_AND_CLASSES);

    @Mock
    private DomainObjectContainer mockContainer;

    private FixedAssetRoles fixedAssetRoles;

    private FixedAsset asset;

    private Party party;

    private FixedAssetRoleType type;

    private LocalDate startDate;

    private LocalDate endDate;

    @Before
    public void setUp() throws Exception {
        fixedAssetRoles = new FixedAssetRoles();
        fixedAssetRoles.setContainer(mockContainer);
        
        asset = new FixedAssetForTesting();
        party = new PartyForTesting();
        type = FixedAssetRoleType.ASSET_MANAGER;
        startDate = new LocalDate(2013,4,1);
        endDate = new LocalDate(2013,5,2);
    }
    
    @Test
    public void test() {
        final FixedAssetRole result = new FixedAssetRole();
        context.checking(new Expectations() {
            {
                oneOf(mockContainer).newTransientInstance(FixedAssetRole.class);
                will(returnValue(result));
                
                oneOf(mockContainer).persist(result);
            }
        });
        
        final FixedAssetRole role = fixedAssetRoles.newRole(asset, party, type, startDate, endDate);
        assertThat(role.getAsset(), is(asset));
        assertThat(role.getParty(), is(party));
        assertThat(role.getType(), is(type));
        assertThat(role.getStartDate(), is(startDate));
        assertThat(role.getEndDate(), is(endDate));
    }

}