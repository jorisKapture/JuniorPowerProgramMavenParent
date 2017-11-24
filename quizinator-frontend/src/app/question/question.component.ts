import {Component, Input, OnInit} from '@angular/core';
import {Question} from '../model/question';
import {QuestionService} from '../service/question.service';
import {Subscription} from 'rxjs/Subscription';
import {RouterModule} from '@angular/router';
import {Router} from '@angular/router';
import {ThemeService} from '../service/theme.service';
import {Theme} from '../model/theme';
import {TagService} from '../service/tag.service';
import {Tag} from '../model/tag';
import {SearchFilter} from "./question.searchfilter";

@Component({
  selector: 'article',
  templateUrl: './question.component.html',
  styleUrls: ['../app.component.css'],
  providers: [QuestionService, ThemeService, TagService]
})

export class QuestionViewComponent implements OnInit {
  private questions: Question[];
  private themes: Theme[];
  tags: Tag[];
  extendedTags: Tag[];
  private answerShown: boolean[] = [];
  private editEnabled: boolean[] = [];
  private answersShown: false;
  private newTags: String = "";
  private tagStrings : String[] = [];
  private newQuestion: Question = new Question;
  filterTag: Tag;
  private editing: false;
  private searchfilter : SearchFilter = new SearchFilter;

  constructor(private questionService: QuestionService, private router: Router, private themeService: ThemeService,
              private tagService: TagService) {
  }

  public showAnswers(): void {
    for (let i = 0; i < this.questions.length; i++) {
      this.answerShown[i] = this.answersShown;
    }
  }

  public changeFilter(): void {
    if(isNaN(this.searchfilter.tagIds[0])){
      this.searchfilter.tagIds = [];
    }
    this.questionService.getQuestionsByFilter(this.searchfilter).then(questions => this.questions = questions);
  }

  public removeTagFromNewQuestion(index: number) {
    this.newQuestion.tags.splice(index, 1);
  }

  public updateQuestion(questionindex): void {

    this.updateQuestionTags(questionindex)
      .then(() => this.updateQuestionTheme(questionindex))
      .then(() => this.saveUpdatedQuestion(this.questions[questionindex]));
  }

  private updateQuestionTags(i): Promise<Tag[]>{
    return this.tagService.findCreateTags(this.tagStrings[i]).then(res => this.questions[i].tags = res);
  }

  public saveUpdatedQuestion(question): void {
    this.questionService.saveQuestion(question).then(() => this.changeFilter());
  }

  private updateQuestionTheme(i) : Promise<Theme>{
    return this.themeService.saveOrCreateTheme(
      this.questions[i].theme.name
    ).then(
      res => this.questions[i].theme = res
      );
    //.then(() => this.saveUpdatedQuestion(this.questions[i]));
  }

  ngOnInit(): void {
    this.newQuestion.tags = [];
    this.getQuestions();
    this.getThemes();
    this.getTags();
    this.searchfilter.tagIds = [];
  }

  public saveQuestion(): void {
    this.tagService.findCreateTags(this.newTags)
      .then(result => this.newQuestion.tags = result)
      .then( () => this.questionService.saveQuestion(this.newQuestion))
      .then(() => this.changeFilter());
  }

  public deleteQuestion(id): void {
    this.questionService.deleteQuestion(id).then(() => this.getQuestions());
  }

  private getQuestions(): void {
    this.questionService.getQuestions().then(questions => this.questions = questions).then(() => this.fillTagStrings());
    this.newQuestion.question = '';
    this.newQuestion.answer = '';
    this.newQuestion.url = '';
  }

  private fillTagStrings() : void{
    for(var i=0; i<this.questions.length; i++){
      this.tagStrings[i]= this.joinTags(this.questions[i].tags);
    }
  }

  private joinTags(questiontags) : string{
    var tagnames = [];
    for(var i=0;i<questiontags.length;i++){
      tagnames.push(questiontags[i].name);
    }
    return tagnames.join(',');
  }

  private getThemes(): void {
    this.themeService.getThemes().then((themes => this.themes = themes));
  }

  private setTags(tags: Tag[]): void {
    this.tags = tags;
  }

  private getTags(): void {
    this.tagService.getTags().then((tags => this.setTags(tags)));
  }
}
