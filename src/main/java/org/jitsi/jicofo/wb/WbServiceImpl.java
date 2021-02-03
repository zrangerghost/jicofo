package org.jitsi.jicofo.wb;

import org.jitsi.jicofo.*;
import org.jxmpp.jid.EntityBareJid;

import java.util.List;
import java.util.Objects;

/**
 * @author GHOST
 * @title: WbServiceImpl
 * @description: TODO
 *
 */
public class WbServiceImpl implements WbService{

    private JitsiMeetConference conference;

    private JicofoServices jicofoServices;


    public void init(){
        jicofoServices =  Objects.requireNonNull(JicofoServices.jicofoServicesSingleton);
    }



    public void sendMessage(EntityBareJid roomName, String sid, String message){
        JitsiMeetConferenceImpl conference = getFocusManager().getConference(roomName);
        if(conference == null){
            throw new IllegalArgumentException("can not find conference by " + roomName);
        }

        List<Participant> participants = conference.getAllParticipants();
        participants.stream().forEach(participant ->{
            if(participant.getJingleSession().getSessionID().equals(sid))
                return;
            //像所有会议室参与者发送消息
            //TODO
        });
    }

    public FocusManager getFocusManager(){
        return jicofoServices.getFocusManager();
    }



}
