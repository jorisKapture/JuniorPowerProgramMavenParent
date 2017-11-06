import {Component} from '@angular/core'
import {Question} from "../model/question";
import {QuestionService} from "../service/question.service";
import {Subscription} from "rxjs/Subscription";
import {RouterModule} from "@angular/router";
import {Router} from "@angular/router";

@Component({
  selector: 'article',
  templateUrl: './question.component.html',
  providers: [QuestionService]
})

export class QuestionViewComponent{
  private questions : Question[];
  private newQuestion : Question = new Question;

  constructor(private questionService: QuestionService, private router : Router){
  }

  ngOnInit(): void {
    this.getQuestions();
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
}
