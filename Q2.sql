SELECT users.user_code, COUNT(orders.tote_number) AS num_totes_packed
FROM (
  SELECT user_code
  FROM order_tote_process_log
) AS users
LEFT JOIN (
  SELECT user_code, tote_number
  FROM order_tote_process_log
  WHERE action_code = 'PACKED'
    AND time(process_date) BETWEEN time('10:00:00') AND time('10:59:59')
    AND date(process_date) = '2023-11-01' -- Change to date('now') for today's hourly reports
) AS orders ON users.user_code = orders.user_code
GROUP BY users.user_code;


/*
Expected output:
user_code | num_totes_packed
P1        | 1
P2        | 0
SYS       | 0
*/

