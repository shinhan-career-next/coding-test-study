# main 브랜치 보호 설정

`main` 브랜치 보호는 저장소 파일이 아니라 GitHub 서버에서 강제해야 합니다. 원격 저장소를 만든 뒤 저장소 관리자 계정으로 아래 스크립트를 한 번 실행합니다.

```bash
./.github/scripts/protect-main.sh OWNER/REPOSITORY
```

예시:

```bash
./.github/scripts/protect-main.sh JaeHyun10-03/scn-coding-test-study
```

이 설정은 다음 규칙을 적용합니다.

- `main` 변경 시 Pull Request 필수
- 관리자에게도 동일한 규칙 적용
- 강제 푸시 금지
- `main` 브랜치 삭제 금지
- PR 대화가 해결되어야 merge 가능
- 별도의 승인 리뷰 수는 요구하지 않음

GitHub 웹 화면에서는 `Settings → Branches → Branch protection rules`에서 같은 내용을 확인할 수 있습니다.

> 저장소 요금제나 조직 정책에 따라 일부 보호 기능을 사용할 수 없을 수 있습니다. 스크립트 실행 결과가 성공인지 반드시 확인합니다.
