package org.estatio.dom.party.contributed;

import java.util.SortedSet;

import javax.inject.Inject;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.NotContributed;
import org.apache.isis.applib.annotation.NotContributed.As;

import org.estatio.dom.communicationchannel.CommunicationChannel;
import org.estatio.dom.communicationchannel.CommunicationChannelType;
import org.estatio.dom.communicationchannel.CommunicationChannels;
import org.estatio.dom.party.Party;

@DomainService
@Hidden
public class PartyCommunicationChannelContributions {

    @ActionSemantics(Of.SAFE)
    @NotContributed(As.ACTION)
    public String phoneNumber(Party party) {
        return channelTitle(party, CommunicationChannelType.PHONE_NUMBER);
    }

    @ActionSemantics(Of.SAFE)
    @NotContributed(As.ACTION)
    public String emailAddress(Party party) {
        return channelTitle(party, CommunicationChannelType.EMAIL_ADDRESS);
    }

    private String channelTitle(Party party, final CommunicationChannelType type) {
        final SortedSet<CommunicationChannel> results = communicationChannels.findByOwnerAndType(party, type);
        if (!results.isEmpty()) {
            return container.titleOf(results.first());
        }
        return null;
    }

    @Inject
    private CommunicationChannels communicationChannels;

    @Inject
    private DomainObjectContainer container;

}