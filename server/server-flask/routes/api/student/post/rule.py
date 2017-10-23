from flask_restful_swagger_2 import Resource, request, swagger

from db.models.post import RuleModel

from . import rule_doc
from . import helper


class RuleList(Resource):
    @swagger.doc(rule_doc.RULE_LIST)
    def get(self):
        return helper.list(RuleModel), 200


class Rule(Resource):
    @swagger.doc(rule_doc.RULE)
    def get(self):
        id = request.args.get('id')

        rule = helper.inquire(RuleModel, id)

        return (rule, 200) if rule else ('', 204)