import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {SearchPageService} from "./search-page.service";
import {SearchPageInput} from "./search-page-input.model";
import {MatInput} from "@angular/material/input";

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.scss']
})
export class SearchPageComponent implements OnInit {

  public firstName: string;
  public secondName: string;

  out: any;

  constructor(private searchPageService: SearchPageService) { }

  ngOnInit(): void {
  }

  search() {
    this.searchPageService.get(
      {
        'firstName': this.firstName,
        'secondName': this.secondName
      } as SearchPageInput
    ).subscribe(result =>
      this.out = result
    )
  }
}
