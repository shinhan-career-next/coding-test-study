def solution(phone_book):
    answer = True
    phone_set = set(phone_book)
    for phone in phone_book:
        for i in range(1, len(phone)):
            prefix = phone[:i]
            if prefix in phone_set:
                return False
    return answer