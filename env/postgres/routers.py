import psycopg2
import psycopg2.extras
import uuid
import random

# Connect to your postgres DB
conn = psycopg2.connect(user="airuser",
                        password="airuser",
                        host="localhost",
                        port="5432",
                        database="airport")

# Open a cursor to perform database operations
cur = conn.cursor(cursor_factory=psycopg2.extras.RealDictCursor)

# Execute a query
cur.execute("SELECT id FROM airports")

# Retrieve query results
records = cur.fetchall()

count = len(records) - 1

for val in records:
    print(val["id"], "\n")
    for _ in range(random.randint(0, 5)):
        num = random.randint(1, count)
        reference = records[num]
        cur.execute("INSERT INTO routers (id, from_airport_id, to_airport_id) VALUES(%s, %s, %s)", (str(uuid.uuid4()), str(val["id"]), str(reference["id"])))

conn.commit()
cur.close()
conn.close()
