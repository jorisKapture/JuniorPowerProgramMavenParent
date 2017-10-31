import {Component} from '@angular/core'
import {Tag} from "../model/tag";
import {TagService} from "../service/tag.service";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'article',
  templateUrl: './tag.component.html',
  providers: [TagService]
})

export class TagViewComponent{
  private tags : Tag[];

  constructor(private tagService: TagService){
  }

  ngOnInit(): void {
    this.getTags();
    console.log(this.tags);
  }

  private getTags() : void{
    this.tagService.getTags().then(tags => this.tags = tags);
  }
}
