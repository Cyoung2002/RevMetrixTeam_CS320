package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.ycp.cs320.lab02.model.Event;

public class EventServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ArrayList<Event> eventList;

    @Override
    public void init() throws ServletException {
        eventList = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Event Servlet: doGet");

        if (eventList.isEmpty()) {
            eventList.add(new Event("Event A", "Practice", "York, PA", "Session One", 10.7, 3));
        }

        request.setAttribute("events", eventList);
        request.getRequestDispatcher("/_view/event.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);

        if ("addNew".equals(action)) {
            System.out.println("addNew action");

            String name = request.getParameter("name");
            String type = request.getParameter("type");
            String location = request.getParameter("location");
            String session = request.getParameter("session");
            double eventStats = Double.parseDouble(request.getParameter("eventStats"));
            int standings = Integer.parseInt(request.getParameter("standings"));

            Event newEvent = new Event(name, type, location, session, eventStats, standings);
            
            if (!eventList.contains(newEvent)) {
                eventList.add(newEvent);
            } else {
                response.getWriter().println("<html><body><h3>Error: This event is already in the list!</h3></body></html>");
                return;
            }

            System.out.println("New event added");
            for (Event event : eventList) {
                System.out.println(event.getName() + " - " + event.getType() + " - " + event.getLocation());
            }
        } else if ("delete".equals(action)) {
            System.out.println("delete action");

            String[] eventData = request.getParameter("selectedEventDelete").split(",");

            if (eventData.length < 6) {
                System.out.println("Error: selectedEventDelete does not contain enough data.");
                response.getWriter().println("<html><body><h3>Error: Invalid event data format!</h3></body></html>");
                return;
            }

            Event eventToDelete = new Event(eventData[0], eventData[1], eventData[2], eventData[3], 
                                            Double.parseDouble(eventData[4]), Integer.parseInt(eventData[5]));
            eventList.remove(eventToDelete);
            System.out.println("Event deleted");

        }

        System.out.println("Sending redirect");
        response.sendRedirect(request.getContextPath() + "/event");
    }
}
