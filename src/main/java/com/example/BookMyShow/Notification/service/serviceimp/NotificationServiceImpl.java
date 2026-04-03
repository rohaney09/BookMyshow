package com.example.BookMyShow.Notification.service.serviceimp;

import com.example.BookMyShow.Movie.document.Movie;
import com.example.BookMyShow.Notification.model.Notification;
import com.example.BookMyShow.Notification.repository.NotificationRepository;
import com.example.BookMyShow.Notification.service.NotificationService;
import com.example.BookMyShow.User.model.User;
import com.example.BookMyShow.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public void notifyNewMovie(Movie movie) {

        List<User> users = userRepository.findAll();

        for (User user : users) {
            Notification notification = new Notification();
            notification.setUserId(user.getUserId());
            notification.setMovieId(movie.getId());
            notification.setMessage(
                    "New movie added: " + movie.getTitle()
            );
            notification.setSentAt(LocalDateTime.now());

            notificationRepository.save(notification);

        }
    }

    public List<Notification> getAllNotification() {

        List<Notification> all = notificationRepository.findAll();

        return all;
    }
}
