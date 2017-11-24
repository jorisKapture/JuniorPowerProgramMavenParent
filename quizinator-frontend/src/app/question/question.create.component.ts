import {Component, Input, OnInit} from '@angular/core';
import {QuestionService} from '../service/question.service';
import {Router} from '@angular/router';
import {ThemeService} from '../service/theme.service';
import {Theme} from '../model/theme';
import {TagService} from '../service/tag.service';

@Component({
  selector: 'article',
  templateUrl: './question.create.component.html',
  styleUrls: ['../app.component.css'],
  providers: [QuestionService, ThemeService, TagService]
})

export class QuestionCreateViewComponent implements OnInit {
  themes: Theme[];
  urls: String = '';
  themename: String = '';
  tagsstring: String = '';

  constructor(private questionService: QuestionService, private router: Router, private themeService: ThemeService,
              private tagService: TagService) {
  }

  public GenerateQuestions(): void {

    this.questionService.saveBulk(this.urls, this.tagsstring, this.themename)
      .then(() => this.ClearInputs());
  }

  public ClearInputs(): void {
    this.urls = '';
    this.themename = '';
    this.tagsstring = '';
  }

  public LogFields(): void {
    console.log(this.urls);
    console.log(this.tagsstring);
    console.log(this.themename);
  }

  ngOnInit(): void {
    this.getThemes();
  }

  private getThemes(): void {
    this.themeService.getThemes().then((themes => this.themes = themes));
  }

}
