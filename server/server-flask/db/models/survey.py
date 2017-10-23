from datetime import date

from db.models.account import StudentModel
from db.mongo import *


class QuestionModel(EmbeddedDocument):
    title = StringField(required=True)
    is_objective = BooleanField(required=True)
    choice_paper = ListField()


class SurveyModel(Document):
    title = StringField(required=True)
    start_date = StringField(required=True)
    end_date = StringField(required=True)
    target = IntField(required=True)
    questions = ListField(EmbeddedDocumentField(QuestionModel))

    creation_date = StringField(required=True, default=str(date.today()))


class AnswerModel(Document):
    answer_student = ReferenceField(StudentModel)
    survey = ReferenceField(SurveyModel)
    answers = ListField(StringField())
