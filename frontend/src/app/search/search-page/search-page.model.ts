export class SearchInput {
  sourceName: string;
  targetName: string;
}

export class TypeCastInfo {
  name: string;
  isTypeCasted: boolean;
  genres: any;
}

export class Coincidence {
  sourceName: string;
  targetName: string;
  commonTitles: [];
}


export class LinkLevel {
  sourceName: string;
  targetName: string;
  levelOfSeparation: number;
}
