create database social_media_db;
use social_media_db;
create table tUser(user_id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(50) UNIQUE NOT NULL,
					email VARCHAR(100) UNIQUE NOT NULL, password_hash VARCHAR(255) NOT NULL,
                    full_name VARCHAR(100), date_of_birth DATE,phone VARCHAR(20),
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,last_login TIMESTAMP);

create table tFriends(friendship_id INT PRIMARY KEY AUTO_INCREMENT,
					   user_id INT NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES tUser(user_id) ON DELETE CASCADE,
					   friend_id INT NOT NULL,
					   FOREIGN KEY (friend_id) REFERENCES tUser(user_id) ON DELETE CASCADE,
                       status ENUM('pending', 'accepted', 'blocked'),created_at TIMESTAMP);
                       
create table tPosts( post_id INT PRIMARY KEY AUTO_INCREMENT,user_id INT NOT NULL,
					FOREIGN KEY(user_id) REFERENCES tUser(User_id) ON DELETE CASCADE,
                    content TEXT NOT NULL,image_url VARCHAR(255),
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    likes_count INT DEFAULT 0);
                    
create table tComments(comment_id INT PRIMARY KEY AUTO_INCREMENT,
						post_id INT NOT NULL,
                        FOREIGN KEY(post_id) REFERENCES tPosts(post_id) ON DELETE CASCADE,
                        user_id INT NOT NULL,
						FOREIGN KEY(user_id) REFERENCES tUser(User_id) ON DELETE CASCADE,
						comment_text TEXT NOT NULL,
                        created_at TIMESTAMP);




-- EXAMPLE DATA FOR UNDERSTANDING
INSERT INTO tUser (username, email, password_hash, full_name, date_of_birth, phone)
VALUES
('HARI', 'hari@gmail.com', 'HK123', 'HARI KRISHNA', '2004-03-25', '9876543210'),
('KRISHNA', 'krishna@yahoo.com', 'KRISHNA577', 'SHRI KRISHNA', '1997-08-21', '9876501234'),
('SHYAM', 'shyam@outlook.com', 'SHYAM@2011', 'SHYAM', '1993-11-03', '9898989898'),
('RAM', 'ram@gmail.com', 'RAM246', 'RAM', '1998-02-15', '9765432190'),
('ravi_kumar', 'ravi@example.com', 'RAVI143', 'Ravi Kumar', '1996-09-09', '9999999999');


INSERT INTO tFriends (user_id, friend_id, status)
VALUES
(1, 2, 'accepted'),   
(1, 3, 'pending'),    
(2, 4, 'accepted'),   
(3, 5, 'accepted'),  
(4, 5, 'blocked'); 

INSERT INTO tPosts (user_id, content, image_url, likes_count)
VALUES
(1, 'GOOD MORNING, HAVE A GOOD DAY', NULL, 15),
(2, 'AT RAM MANDIR TO TAKE BLESSINGS ', 'images/RAM_MANDIR.jpg', 25),
(3, 'STARTED MY JOURNEY WITH MOOLYA', NULL, 8),
(5, 'ON A HOLIDAY', 'images/BEACH.jpg', 18);


INSERT INTO tComments (post_id, user_id, comment_text)
VALUES
(1, 2, 'WISHING U THE SAME'),
(1, 3, 'POSITIVE VIBES....'),
(2, 1, 'SUPERB TEMPLE BROO'),
(3, 4, 'ALL THE BEST BRO, HOPE U WILL GET BEST VIBES'),
(4, 2, 'HAPPY JOURNEY RAVI..');




