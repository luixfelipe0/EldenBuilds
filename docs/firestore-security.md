# Firestore Security Rules

This project stores user builds in the `builds` collection and enforces ownership using `authorId`.

## Rules Strategy

Rules in `firestore.rules` enforce:

- Authenticated access only.
- Read/write restricted to the document owner (`authorId == request.auth.uid`).
- `authorId` cannot be reassigned on updates.
- Basic schema validation for `name`, `level`, optional text fields, and `stats` map.

## Files

- `firestore.rules`: Firestore access and validation rules.
- `firestore.indexes.json`: index definition file (currently empty).
- `firebase.json`: maps Firestore rules/indexes files for deployment.

## Deploy Rules

1. Install Firebase CLI:
   - `npm install -g firebase-tools`
2. Authenticate:
   - `firebase login`
3. Select your Firebase project:
   - `firebase use <your-project-id>`
4. Deploy only Firestore rules:
   - `firebase deploy --only firestore:rules`

Optional full Firestore deploy:

- `firebase deploy --only firestore`

## Local Validation

Use Firebase Emulator Suite before deploying to production:

1. Start emulator:
   - `firebase emulators:start --only firestore`
2. Run app/tests against emulator.
3. Confirm:
   - User A cannot read/write User B documents.
   - Update keeps original `authorId`.
   - Invalid shapes (missing/invalid `name`/`level`/`stats`) are denied.

## Notes

- If your app shape changes (new fields in `Build`), update `hasExpectedKeys()` and validations in `firestore.rules`.
- Rules should be reviewed together with app-level model changes to prevent silent permission failures.
