package app.model;

import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "points")
public class Point {
    transient private final Logger log = LoggerFactory.getLogger(Point.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double x;
    private double y;
    private double r;
    private boolean hitResult;
    private String time;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Point() {}

    public Point(double x, double y, double r, User user, LocalDateTime time) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.user = user;
        this.time = time.toString();
    }

    public long getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public User getUser() {
        return user;
    }

    public boolean isHitResult() {
        return hitResult;
    }

    public String getTime() {
        return time;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHitResult(boolean hitResult) {
        this.hitResult = hitResult;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTime(LocalDateTime time) {
        this.time = time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public void intersectPoint() {
        hitResult = ((x <= 0 && y <= 0) && (y >= -r/2 && x >= -r)  ||
                ((x*x + y*y) <= (r*r/4)) && x <= 0 && y >= 0) ||
                ((y <= r - x) && (x <= r) && (y <= r) && (x >= 0) && (y >= 0));
    }
}
