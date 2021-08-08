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
for _ in range(10):
    passport_uuid = uuid.uuid4()

    passports.append(passport_uuid)

    content.append(("('%s', %d, %d, %d, '%s'),\n" % (
        uuid.uuid4(),
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

