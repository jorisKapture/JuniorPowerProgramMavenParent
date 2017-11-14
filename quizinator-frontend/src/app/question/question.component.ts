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
  private newTag: Tag;
  private newTagName: string;
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
    this.questionService.getQuestionsByFilter(this.searchfilter).then(questions => this.questions = questions);
  }

  public addTagToNewQuestion() {
    let tag: Tag;
    if (!!this.newTagName) {
      tag = new Tag;
      tag.name = this.newTagName;
      this.newQuestion.tags.push(tag);
    } else {
      this.newQuestion.tags.push(this.newTag);
    }
  }

  public removeTagFromNewQuestion(index: number) {
    this.newQuestion.tags.splice(index, 1);
  }

  public updateQuestion(question): void {
    this.questionService.saveQuestion(question).then(() => this.changeFilter());
  }

  ngOnInit(): void {
    this.newQuestion.tags = [];
    this.getQuestions();
    this.getThemes();
    this.getTags();
    this.searchfilter.tagIds = [];
  }

  public saveQuestion(): void {
    this.questionService.saveQuestion(this.newQuestion).then(() => this.changeFilter());
  }

  public deleteQuestion(id): void {
    this.questionService.deleteQuestion(id).then(() => this.getQuestions());
  }

  private getQuestions(): void {
    this.questionService.getQuestions().then(questions => this.questions = questions);
    this.newQuestion.question = '';
    this.newQuestion.answer = '';
    this.newQuestion.url = '';
  }

  private getThemes(): void {
    this.themeService.getThemes().then((themes => this.themes = themes));
  }

  private setTags(tags: Tag[]): void {
    this.tags = tags;
    let defaultTag = new Tag();
    defaultTag.id = 0;
    defaultTag.name = 'all';
    this.extendedTags = tags;
    this.extendedTags.unshift(defaultTag);
  }

  private getTags(): void {
    this.tagService.getTags().then((tags => this.setTags(tags)));
  }
}
