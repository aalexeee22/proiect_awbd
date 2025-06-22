package com.awbd.musicshop.mappers;

import com.awbd.musicshop.domain.Participant;
import com.awbd.musicshop.dtos.ParticipantDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ParticipantMapper {
    ParticipantDTO toDto (Participant participant);
    Participant toParticipant (ParticipantDTO participantDTO);
}
