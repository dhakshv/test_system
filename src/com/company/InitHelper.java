package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitHelper {

    private Map<Integer, Bus> buses = new HashMap<>();
    private Map<Integer, Route> routes = new HashMap<>();
    private Map<Integer, Stop> stops = new HashMap<>();
    private List<Event> listOfEvents = new ArrayList<>();

    public InitHelper(String fileName) {
        processInputFile(fileName);
    }

    private void processInputFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] ar = line.split(",");

                if (ar[0].equals("add_stop")) {
                    Stop stop = new Stop(ar);
                    stops.put(Integer.parseInt(ar[1]), stop);
                } else if (ar[0].equals("add_route")) {
                    Route route = new Route(ar);
                    routes.put(Integer.parseInt(ar[1]), route);
                } else if (ar[0].equals("extend_route")) {
                    Route route = routes.get(Integer.parseInt(ar[1]));
                    route.extendRoute(ar);
                } else if (ar[0].equals("add_bus")) {
                    Route route = routes.get(Integer.parseInt(ar[2]));
                    Bus bus = new Bus(ar, route);
                    buses.put(Integer.parseInt(ar[1]), bus);
                } else if (ar[0].equals("add_event")) {
                    Event event = new Event(ar);
                    listOfEvents.add(event);
                }
            }
        } catch (IOException e) {
            System.out.println("File Read Error");
        }

        processEvents();
    }

    private void processEvents() {
        for (int i = 0; i < 20; i++) {
            if (!listOfEvents.isEmpty()) {
                Event nextEvent = new Event();
                Event newEvent = new Event();
                for (Event e : listOfEvents) {
                    if (nextEvent.getRank() == null || e.getRank() < nextEvent.getRank()) {
                        nextEvent = e;
                    }
                }

                if (nextEvent.getType().equals("move_bus")) {
                    Bus bus = buses.get(nextEvent.getTargetObject());
                    Route route = routes.get(bus.getDesignatedRoute());
                    newEvent = bus.moveBus(route, newEvent, stops, nextEvent.getRank());
                }

                listOfEvents.remove(nextEvent);
                listOfEvents.add(newEvent);
            }
        }
    }
}
