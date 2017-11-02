import {Component} from '@angular/core'
import {Tag} from "../model/tag";
import {TagService} from "../service/tag.service";
import {Subscription} from "rxjs/Subscription";
import {RouterModule} from "@angular/router";
import {Router} from "@angular/router";

@Component({
  selector: 'article',
  templateUrl: './tag.component.html',
  providers: [TagService]
})

export class TagViewComponent{
  private tags : Tag[];
  private newTag : Tag = new Tag;

  constructor(private tagService: TagService, private router : Router){
  }

  ngOnInit(): void {
    this.getTags();
  }

  public saveTag() : void{
    this.tagService.saveTag(this.newTag).then(() => this.getTags());
  }

  private getTags() : void{
    this.tagService.getTags().then(tags => this.tags = tags);
  }
}
