import random
import uuid
from typing import List, AnyStr
from faker import Faker

fake = Faker()

unique_code = {}
unique_number = {}
unique_series = {}


def unique(start, end, unique_list):
    random_int = random.randint(start, end)
    while random_int in unique_list:
        random_int = random.randint(start, end)
    unique_list[random_int] = random_int

    return random_int


file = open('passports.sql', 'r+b')

content: List[AnyStr] = [b'INSERT INTO "passports" ("id", "code", "number", "series", "date") VALUES\n']

passports = []

Faker.seed(0)
for _ in range(5000):
    passport_uuid = uuid.uuid4()

    passports.append(passport_uuid)

    content.append(("('%s', %d, %d, %d, '%s'),\n" % (
        passport_uuid,
        unique(100000, 999999, unique_code),
        unique(1000, 9999, unique_number),
        unique(100000, 999999, unique_series),
        fake.date()
    )).encode("UTF-8"))

content[len(content) - 1] = content[len(content) - 1].decode("UTF-8").replace(",\n", ";\n").encode("UTF-8")

file = open('passports.sql', 'r+b')

file.truncate(0)
file.writelines(content)

file.close()

# ---------------------------------------

emails = {}
usernames = {}


def unique_username():
    username = fake.profile()['username']
    while username in usernames:
        username = fake.profile()['username']

    usernames[username] = username

    return username


def unique_email():
    email = fake.profile()['mail']
    while email in emails:
        email = fake.profile()['mail']

    emails[email] = email

    return email


fake = Faker()

file = open('users.sql', 'r+b')

content: List[AnyStr] = [
    b'INSERT INTO "users" ("id", "email", "first_name", "last_name", "password", "second_name", "username", "passport_id") VALUES\n']

Faker.seed(0)
for i in range(10000):
    Faker.seed(i)
    profile = fake.profile()
    name = profile['name'].split(" ")
    first_name, last_name = name[0], name[1]
    if bool(random.getrandbits(1)):
        second_name = "'" + profile['name'].split(" ")[0] + "'"
    else:
        second_name = 'NULL'

    if passports:
        passport_uuid = "'" + str(passports.pop()) + "'"
    else:
        passport_uuid = 'NULL'

    content.append(("('%s', '%s', '%s', '%s', '%s', %s, '%s', %s),\n" % (
        uuid.uuid4(),
        unique_email(),
        first_name,
        last_name,
        '{bcrypt}$2a$10$K52g4DBPnokbVO0mXMZYKuf40us7CFsVLENB4hGOFaFctEm6.bsqO',
        second_name,
        unique_username(),
        passport_uuid
    )).encode("UTF-8"))

content[len(content) - 1] = content[len(content) - 1].decode("UTF-8").replace(",\n", ";\n").encode("UTF-8")

file = open('users.sql', 'r+b')

file.truncate(0)
file.writelines(content)

file.close()
