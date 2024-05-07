package database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.capstone.passionventure.User

object EnthusiastRegister {
    // Inner class that defines the table contents
    object UserEntry {
        const val TABLE_NAME = "user"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_FULL_NAME = "full_name"
        const val COLUMN_CONTACT = "contact"
        const val COLUMN_IMAGE = "image" // New column for image data
        const val COLUMN_CATEGORY = "category"
        const val COLUMN_ADDRESS = "address"
    }
}

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun addUser(fullName: String, username: String, password: String, email: String, contact: String, category: String, imageData: ByteArray?, address: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(EnthusiastRegister.UserEntry.COLUMN_FULL_NAME, fullName)
            put(EnthusiastRegister.UserEntry.COLUMN_USERNAME, username)
            put(EnthusiastRegister.UserEntry.COLUMN_PASSWORD, password)
            put(EnthusiastRegister.UserEntry.COLUMN_EMAIL, email)
            put(EnthusiastRegister.UserEntry.COLUMN_CONTACT, contact)
            put(EnthusiastRegister.UserEntry.COLUMN_ADDRESS, address) // Add address here
            put(EnthusiastRegister.UserEntry.COLUMN_CATEGORY, category)
            put(EnthusiastRegister.UserEntry.COLUMN_IMAGE, imageData)
        }
        db.insert(EnthusiastRegister.UserEntry.TABLE_NAME, null, values)
    }

    fun getUserData(username: String): User? {
        val db = readableDatabase
        val projection = arrayOf(
            EnthusiastRegister.UserEntry.COLUMN_FULL_NAME,
            EnthusiastRegister.UserEntry.COLUMN_EMAIL,
            EnthusiastRegister.UserEntry.COLUMN_CONTACT,
            EnthusiastRegister.UserEntry.COLUMN_ADDRESS,
            EnthusiastRegister.UserEntry.COLUMN_IMAGE // Include profile image column
        )
        val selection = "${EnthusiastRegister.UserEntry.COLUMN_USERNAME} = ?"
        val selectionArgs = arrayOf(username)
        val cursor = db.query(
            EnthusiastRegister.UserEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var userData: User? = null
        if (cursor.moveToFirst()) {
            val fullName = cursor.getString(cursor.getColumnIndexOrThrow(EnthusiastRegister.UserEntry.COLUMN_FULL_NAME))
            val email = cursor.getString(cursor.getColumnIndexOrThrow(EnthusiastRegister.UserEntry.COLUMN_EMAIL))
            val contact = cursor.getString(cursor.getColumnIndexOrThrow(EnthusiastRegister.UserEntry.COLUMN_CONTACT))
            val address = cursor.getString(cursor.getColumnIndexOrThrow(EnthusiastRegister.UserEntry.COLUMN_ADDRESS))
            val imageData = cursor.getBlob(cursor.getColumnIndexOrThrow(EnthusiastRegister.UserEntry.COLUMN_IMAGE))
            userData = User(fullName, email, contact, address, imageData)
        }
        cursor.close()
        return userData
    }


    fun updateUserData(username: String, fullName: String, email: String, contact: String, address: String, imageData: ByteArray?) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(EnthusiastRegister.UserEntry.COLUMN_FULL_NAME, fullName)
            put(EnthusiastRegister.UserEntry.COLUMN_EMAIL, email)
            put(EnthusiastRegister.UserEntry.COLUMN_CONTACT, contact)
            put(EnthusiastRegister.UserEntry.COLUMN_ADDRESS, address)
            put(EnthusiastRegister.UserEntry.COLUMN_IMAGE, imageData) // Add image data
        }
        val selection = "${EnthusiastRegister.UserEntry.COLUMN_USERNAME} = ?"
        val selectionArgs = arrayOf(username)
        db.update(EnthusiastRegister.UserEntry.TABLE_NAME, values, selection, selectionArgs)
    }



    //hehre is validation and getting vlaues of user
    fun isUserExists(username: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM ${EnthusiastRegister.UserEntry.TABLE_NAME} WHERE ${EnthusiastRegister.UserEntry.COLUMN_USERNAME} = ?"
        val cursor = db.rawQuery(query, arrayOf(username))
        val count = cursor.count
        cursor.close()
        return count > 0
    }

    fun getUserCategory(username: String): String? {
        val db = readableDatabase
        val projection = arrayOf(EnthusiastRegister.UserEntry.COLUMN_CATEGORY)
        val selection = "${EnthusiastRegister.UserEntry.COLUMN_USERNAME} = ?"
        val selectionArgs = arrayOf(username)
        val cursor = db.query(
            EnthusiastRegister.UserEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var category: String? = null
        if (cursor.moveToFirst()) {
            category = cursor.getString(cursor.getColumnIndexOrThrow(EnthusiastRegister.UserEntry.COLUMN_CATEGORY))
        }
        cursor.close()
        return category
    }

    fun deleteUser(username: String) {
        val db = writableDatabase
        val selection = "${EnthusiastRegister.UserEntry.COLUMN_USERNAME} = ?"
        val selectionArgs = arrayOf(username)
        db.delete(EnthusiastRegister.UserEntry.TABLE_NAME, selection, selectionArgs)
    }

    fun getUserImage(username: String): ByteArray? {
        val db = readableDatabase
        val projection = arrayOf(EnthusiastRegister.UserEntry.COLUMN_IMAGE)
        val selection = "${EnthusiastRegister.UserEntry.COLUMN_USERNAME} = ?"
        val selectionArgs = arrayOf(username)
        val cursor = db.query(
            EnthusiastRegister.UserEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var imageData: ByteArray? = null
        if (cursor.moveToFirst()) {
            imageData = cursor.getBlob(cursor.getColumnIndexOrThrow(EnthusiastRegister.UserEntry.COLUMN_IMAGE))
        }
        cursor.close()
        return imageData
    }

    companion object {
        const val DATABASE_VERSION = 3
        const val DATABASE_NAME = "UserRegistration.db"
        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${EnthusiastRegister.UserEntry.TABLE_NAME} (" +
                    "${EnthusiastRegister.UserEntry.COLUMN_USERNAME} TEXT PRIMARY KEY," +
                    "${EnthusiastRegister.UserEntry.COLUMN_PASSWORD} TEXT," +
                    "${EnthusiastRegister.UserEntry.COLUMN_FULL_NAME} TEXT," +
                    "${EnthusiastRegister.UserEntry.COLUMN_EMAIL} TEXT," +
                    "${EnthusiastRegister.UserEntry.COLUMN_CONTACT} TEXT," +
                    "${EnthusiastRegister.UserEntry.COLUMN_ADDRESS} TEXT," +
                    "${EnthusiastRegister.UserEntry.COLUMN_CATEGORY} TEXT," +
                    "${EnthusiastRegister.UserEntry.COLUMN_IMAGE} BLOB)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${EnthusiastRegister.UserEntry.TABLE_NAME}"
    }
}
