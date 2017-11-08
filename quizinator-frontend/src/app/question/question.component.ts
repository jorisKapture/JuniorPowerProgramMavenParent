import {Component} from '@angular/core'
import {Question} from "../model/question";
import {QuestionService} from "../service/question.service";
import {Subscription} from "rxjs/Subscription";
import {RouterModule} from "@angular/router";
import {Router} from "@angular/router";
import {ThemeService} from "../service/theme.service";
import {Theme} from "../model/theme";
import {TagService} from "../service/tag.service";
import {Tag} from "../model/tag";

@Component({
  selector: 'article',
  templateUrl: './question.component.html',
  styleUrls: ['../app.component.css'],
  providers: [QuestionService, ThemeService, TagService]
})

export class QuestionViewComponent{
  private questions : Question[];
  private themes : Theme[];
  private tags : Tag[];
  private newTag : Tag;
  private newTagName : string;
  private newQuestion : Question = new Question;

  constructor(private questionService: QuestionService, private router : Router, private themeService: ThemeService, private tagService : TagService){
    this.newQuestion.tags = [];
  }

  public addTagToNewQuestion(){
    var tag : Tag;
    if(!!this.newTagName){
      tag = new Tag;
      tag.name = this.newTagName;
      this.newQuestion.tags.push(tag);
    } else{
      this.newQuestion.tags.push(this.newTag);
    }
  }

  public removeTagFromNewQuestion(index:number){
    this.newQuestion.tags.splice(index, 1);
  }

  ngOnInit(): void {
    this.getQuestions();
    this.getThemes();
    this.getTags();
  }

  public saveQuestion() : void{
    this.questionService.saveQuestion(this.newQuestion).then(() => this.getQuestions());
  }

  public deleteQuestion(id) : void{
    this.questionService.deleteQuestion(id).then(()=>this.getQuestions());
  }

  private getQuestions() : void{
    this.questionService.getQuestions().then(questions => this.questions = questions);
  }

  private getThemes() : void{
    this.themeService.getThemes().then((themes => this.themes = themes));
  }

  private getTags() : void{
    this.tagService.getTags().then((tags => this.tags = tags));
  }
}
