package com.moonpi.swiftnotes

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.tinkoff.allure.annotations.Description


@RunWith(AndroidJUnit4::class)
class CheckAppTest{
    private val TITLE = "Swiftnotes"
    private val NO_NOTES_DESCRIPTION = "Press '+' to add new note"
    private val NOTE_TITLE_FIELD_TEXT = "Title"
    private val NOTE_BODY_FIELD_TEXT = "Note"
    private val DIALOG_TEXT = "Save changes?"
    private val NOTE_TITLE = "Заметка 1"
    private val NOTE_BODY = "Тестовая запись 1"
    private val HIDE_NOTE_TEXT = "Hide note body in list"
    private val NOTE_FONT_SIZE_TEXT = "Note font size"
    private val RATE_APP_TEXT = "Rate app"
    private val RESTORE_NOTES_TEXT = "Restore notes"
    private val BACKUP_NOTES_TEXT = "Backup notes"

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    var app = SwiftNotesApplication();

    @Test()
    @Description("Проверка состава активити и наличия диалогового окна при выходе с формы редактирования заметки")
    fun checkAppTest() {
        app
            .checkApplicationTitle(TITLE)
            .checkNoNotesPlaceholder(NO_NOTES_DESCRIPTION)
            .clickOnNewNoteButton()
            .checkNoteTitlePlaceholder(NOTE_TITLE_FIELD_TEXT)
            .checkNoteBodyPlaceholder(NOTE_BODY_FIELD_TEXT)
            .clickOnBackButton()
            .checkDialogMessage(DIALOG_TEXT)
            .checkAcceptButtonOnDialog()
            .checkCancelButtonOnDialog()
            .pressCancelButtonOnDialog()
    }


    @Test
    @Description("Проверка создания заметки и её наличия в списке заметок после сохранения")
    fun createNoteTest() {
        app
            .clickOnNewNoteButton()
            .typeNoteTitle(NOTE_TITLE)
            .typeNoteBody(NOTE_BODY)
            .clickOnBackButton()
            .pressAcceptButtonOnDialog()
            .checkNotesCount(1)
            .checkNoteWithTitleAndBody(NOTE_TITLE,NOTE_BODY )
    }

    @Test
    @Description("Проверка содержимого меню на начальном экране и при редактировании заметки")
    fun checkMenuTest() {
        app
            .pressMoreButton()
            .checkMenuItem(BACKUP_NOTES_TEXT)
            .checkMenuItem(RESTORE_NOTES_TEXT)
            .checkMenuItem(RATE_APP_TEXT)
            .pressBackButtonOnDevice()
            .clickOnNewNoteButton()
            .pressMoreButton()
            .checkMenuItem(NOTE_FONT_SIZE_TEXT)
            .checkMenuItem(HIDE_NOTE_TEXT)
    }
}
