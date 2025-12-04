-- Fetching all the information from each table
select * from tuser;
select * from tfriends;
select * from tposts;
select * from tComments;



-- Q1: Fetch all information for a user given their username

SELECT * FROM tUser
WHERE username = 'HARI';

-- Q2: Get all posts by a specific user, sorted by latest first
SELECT 
    p.post_id,
    u.username,
    p.content,
    p.image_url,
    p.likes_count,
    p.created_at
FROM tPosts as p
JOIN tUser as u ON p.user_id = u.user_id
WHERE u.username = 'KRISHNA'
ORDER BY p.created_at DESC;

-- Q3: Find all friends of a user with 'accepted' status

SELECT 
    f.friendship_id,
    u.username AS user_name,
    u2.username AS friend_name,
    f.status,
    f.created_at
FROM tFriends f
JOIN tUser u ON f.user_id = u.user_id
JOIN tUser u2 ON f.friend_id = u2.user_id
WHERE u.username = 'HARI' AND f.status='accepted';

-- Q4: Get all posts with more than 10 likes
SELECT 
    p.post_id,
    p.user_id,
    u.username AS author,
    p.content,
    p.image_url,
    p.likes_count,
    p.created_at
FROM tPosts as p
JOIN tUser as u ON p.user_id = u.user_id
WHERE p.likes_count > 10
ORDER BY p.likes_count DESC;

-- Q5: Find users who have not posted in the last 30 days

SELECT 
    u.user_id,
    u.username
FROM tUser as u
LEFT JOIN tPosts as p 
    ON u.user_id = p.user_id 
    AND p.created_at >= DATE_SUB(NOW(), INTERVAL 30 DAY)
WHERE p.post_id IS NULL;


-- Q6: Calculate average number of posts per user
SELECT 
    COUNT(p.post_id) AS total_posts,
    COUNT(DISTINCT u.user_id) AS total_users,
    COUNT(p.post_id) / COUNT(DISTINCT u.user_id) AS avg_posts_per_user
FROM tUseras as u
LEFT JOIN tPosts as p ON u.user_id = p.user_id;

-- Q7: Find the top 5 users with most friends

SELECT u.username, COUNT(*) AS total_friends
	FROM tFriends as tf
	JOIN tUser as u 
		ON u.user_id = tf.user_id OR u.user_id = tf.friend_id
	WHERE tf.status = 'accepted'
	GROUP BY u.user_id, u.username
	ORDER BY total_friends DESC
	LIMIT 5;
    
-- Q8: Get all comments for a specific post along with user details

SELECT c.comment_id, u.user_id, u.username, u.full_name, u.email, c.comment_text, c.created_at
FROM tComments as c
JOIN tUser as u ON c.user_id = u.user_id
WHERE c.post_id = 2
ORDER BY c.created_at ASC;

-- Q9:  Find mutual friends between two users

SELECT 
    u.username AS mutual_friend
FROM tfriends f1
JOIN tfriends f2 ON f1.friend_id = f2.friend_id
JOIN tUser u ON f1.friend_id = u.user_id
WHERE f1.user_id = (SELECT user_id FROM tUser WHERE username = 'HARI')
  AND f2.user_id = (SELECT user_id FROM tUser WHERE username = 'KRISHNA')
  AND f1.status = 'accepted'
  AND f2.status= 'accepted';


-- Q10: Delete all posts older than 1 year
SET SQL_SAFE_UPDATES = 0;
DELETE FROM tPosts
WHERE created_at < (NOW() - INTERVAL 1 YEAR);
drop table tuser;
