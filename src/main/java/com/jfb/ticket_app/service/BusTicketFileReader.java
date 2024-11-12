package com.jfb.ticket_app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfb.ticket_app.model.ticket.BusTicket;
import com.jfb.ticket_app.service.exceptions.InvalidTicketDataException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class BusTicketFileReader {

  @Value("classpath:ticketData.txt")
  Resource resourceFile;

  private final ObjectMapper objectMapper = new ObjectMapper();

  public List<BusTicket> getBusTickets() throws IOException {
    List<String> ticketStrings = readBusTicketsFromFile();
    List<BusTicket> busTickets = new ArrayList<>();

    for (String ticketString : ticketStrings) {
      BusTicket busTicket = mapBusTicketFromString(ticketString);
      busTickets.add(busTicket);
    }

    return busTickets;
  }

  private List<String> readBusTicketsFromFile() throws IOException {
    List<String> lines = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceFile.getInputStream()))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.replace("“", "\"").replace("”", "\"");
        lines.add(line);
      }
    }
    return lines;
  }

  private BusTicket mapBusTicketFromString(String ticketString) throws InvalidTicketDataException {
    try {
      return objectMapper.readValue(ticketString, BusTicket.class);
    } catch (JsonProcessingException e) {
      throw new InvalidTicketDataException("Invalid ticket data: " + ticketString, e);
    }
  }

}
